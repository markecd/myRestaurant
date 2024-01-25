import './Kanban.css';
import React, { useState } from 'react';
import NarociloKuhar from '../../components/NarociloKuhar/NarociloKuhar';

const initialIzdelki = {
  prejeto: [{ id: 1, name: 'NarociloKuhar 1' }, { id: 2, name: 'NarociloKuhar 2' }],
  vPripravi: [],
  pripravljeno: [],
};

const KanbanBoard = () => {
  const [narocilaKuhar, setNarocilaKuhar] = useState(initialIzdelki);

  const moveForward = (movedIzdelek) => {
    setNarocilaKuhar((prevIzdelki) => {
      const novaNarocila = { ...prevIzdelki };

      if (novaNarocila.prejeto.includes(movedIzdelek)) {
        novaNarocila.prejeto = novaNarocila.prejeto.filter(
          (izdelek) => izdelek.id !== movedIzdelek.id
        );
        novaNarocila.vPripravi.push(movedIzdelek);
      } else if (novaNarocila.vPripravi.includes(movedIzdelek)) {
        novaNarocila.vPripravi = novaNarocila.vPripravi.filter(
          (izdelek) => izdelek.id !== movedIzdelek.id
        );
        novaNarocila.pripravljeno.push(movedIzdelek);
      }

      return novaNarocila;
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
