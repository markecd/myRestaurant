import './Kanban.css';
import React, { useState, useEffect } from 'react';
import NarociloKuhar from '../../components/NarociloKuhar/NarociloKuhar';

const KanbanBoard = () => {
  const [narocilaKuhar, setNarocilaKuhar] = useState({
    prejeto: [],
    vPripravi: [],
    pripravljeno: [],
  });

  useEffect(() => {
    const fetchNarocila = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/v1/narocila/vrniNarocilaKuhar");
        const narocila = await response.json();

        let kategorije = {
          prejeto: [],
          vPripravi: [],
          pripravljeno: [],
        };

        for (const trenutnoNarocilo of narocila) {
          const response = await fetch(`http://localhost:8080/api/v1/izdelki/vrniIzdelkeNarocila/${trenutnoNarocilo.id}`);
          const izdelki = await response.json();

          const narocilo = {
            id: trenutnoNarocilo.id,
            stanje: trenutnoNarocilo.stanje_narocila,
            izdelki: izdelki,
          };

          if (trenutnoNarocilo.stanje_narocila == 'PREJETO') {
            kategorije.prejeto.push(narocilo);
          } else if (trenutnoNarocilo.stanje_narocila == 'V_PRIPRAVI') {
            kategorije.vPripravi.push(narocilo);
          } else {
            kategorije.pripravljeno.push(narocilo);
          }

        }

        setNarocilaKuhar(kategorije);
      } catch (error) {
        console.error('Error fetching narocila:', error);
      }
    };

    fetchNarocila();
  }, []);

  const moveForward = async (movedNarocilo) => {
    let stanje;
    if (movedNarocilo.stanje === 'PREJETO') {
      stanje = 'V_PRIPRAVI';
    } else{
      stanje = 'PRIPRAVLJENO';
    }
    const response1 = await fetch(`http://localhost:8080/api/v1/narocila/spremeniStanje/${stanje}/${movedNarocilo.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    await response1.json();
    setNarocilaKuhar(prevNarocila => {
      let updatedPrejeto = [...prevNarocila.prejeto];
      let updatedVPripravi = [...prevNarocila.vPripravi];
      let updatedPripravljeno = [...prevNarocila.pripravljeno];

      if (movedNarocilo.stanje === 'PREJETO') {
        updatedPrejeto = updatedPrejeto.filter(narocilo => narocilo.id !== movedNarocilo.id);
        movedNarocilo.stanje = 'V_PRIPRAVI';
        updatedVPripravi.push(movedNarocilo);
      } else if (movedNarocilo.stanje === 'V_PRIPRAVI') {
        updatedVPripravi = updatedVPripravi.filter(narocilo => narocilo.id !== movedNarocilo.id);
        movedNarocilo.stanje = 'PRIPRAVLJENO';
        updatedPripravljeno.push(movedNarocilo);
      }

      let kategor = {
        prejeto: updatedPrejeto,
        vPripravi: updatedVPripravi,
        pripravljeno: updatedPripravljeno,
      }

      return kategor;
    });
  };




  return (
    <div className="kanban-board row">
      <div className="col-lg-4">
        <h3 className="naslov-steze">Prejeto</h3>
        {narocilaKuhar.prejeto.map((narocilo) => (
          <NarociloKuhar key={narocilo.id} narocilo={narocilo} moveForward={moveForward} />
        ))}
      </div>
      <div className="col-lg-4 sredina">
        <h3 className="naslov-steze">V Pripravi</h3>
        {narocilaKuhar.vPripravi.map((narocilo) => (
          <NarociloKuhar key={narocilo.id} narocilo={narocilo} moveForward={moveForward} />
        ))}
      </div>
      <div className="col-lg-4">
        <h3 className="naslov-steze">Pripravljeno</h3>
        {narocilaKuhar.pripravljeno.map((narocilo) => (
          <NarociloKuhar key={narocilo.id} narocilo={narocilo} moveForward={moveForward} />
        ))}
      </div>
    </div>
  );
};

export default KanbanBoard;
