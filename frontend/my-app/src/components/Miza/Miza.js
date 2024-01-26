import React, { useState, useEffect } from 'react';
import './Miza.css';
import { Button, Modal, Dropdown, Form } from 'react-bootstrap';
import { useHistory } from 'react-router-dom';

function Miza({ data }) {
  const [stanjeMize, setStanjeMize] = useState(data[1]);
  const [showModal, setShowModal] = useState(false);
  const [novoStanje, setNovoStanje] = useState('');
  const history = useHistory();

  useEffect(() => {
    if (stanjeMize !== data[1]) {
      setStanjeMize(data[1]);
    }
  }, [data[1]]);

  const handleNarociloClick = () => {
    history.push(`/narocilo/${data[0]}`);
  };

  const handleRacunClick = async (id) => {
    const novoStanje = stanjeMize === 'ZASEDENO_POSTREZENO' ? 'NEZASEDENO' : 'ZASEDENO_POSTREZENO';
    setStanjeMize(novoStanje);

    const response1 = await fetch(`http://localhost:8080/api/v1/mize/posodobiStanje/${id}/${novoStanje}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const data1 = await response1.json();
    console.log(data1);

    const response2 = await fetch(`http://localhost:8080/api/v1/narocila/dobiZadnjeNarociloByMiza/${id}`);
    const data2 = await response2.json();
    console.log(data2);

    const response3 = await fetch(`http://localhost:8080/api/v1/narocila/spremeniStanje/PLACANO/${data2}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const data3 = await response3.json();
    console.log(data3);

    const racun = {
      narociloId: data2,
      natakarId: sessionStorage.getItem('id')
    }

    const response4 = await fetch(`http://localhost:8080/api/v1/racuni/vstaviRacun`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(racun)
    });
    const data4 = await response4.json();
    console.log(data4);


  }

  const handleShowModal = () => setShowModal(true);
  const handleCloseModal = () => {
    setShowModal(false);
    setNovoStanje('');
  };

  const handleStanjeChange = async (id) => {
    const response = await fetch(`http://localhost:8080/api/v1/mize/posodobiStanje/${id}/${novoStanje}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
    });
    const data = await response.json();
    console.log(data);
    setStanjeMize(novoStanje);
    handleCloseModal();
  };

  const handlePostregelClick = async (id) => {
    const novoStanje = stanjeMize === 'ZASEDENO_NEPOSTREZENO' ? 'ZASEDENO_POSTREZENO' : 'ZASEDENO_NEPOSTREZENO';
    setStanjeMize(novoStanje);

    const response1 = await fetch(`http://localhost:8080/api/v1/mize/posodobiStanje/${id}/${novoStanje}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const data1 = await response1.json();
    console.log(data1);

    const response2 = await fetch(`http://localhost:8080/api/v1/narocila/dobiZadnjeNarociloByMiza/${id}`);
    const idNarocilo = await response2.json();
    console.log(idNarocilo);

    const response3 = await fetch(`http://localhost:8080/api/v1/narocila/spremeniStanje/POSTREZENO/${idNarocilo}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      }
    });
    const data3 = await response3.json();
    console.log(data3);

  }

  let mizaBorder = data[4] === 'PRIPRAVLJENO' ? {
    border: '2px solid green',
    boxShadow: '0px 0px 10px green'
  } : data[4] === 'V_PRIPRAVI' ? {
    border: '2px solid yellow',
    boxShadow: '0px 0px 10px yellow'
  } : {};


  let stanjeMizeClass;
  let stanjeMizeNapis;

  switch (stanjeMize) {
    case ('NEZASEDENO'):
      stanjeMizeNapis = 'NEZASEDENO';
      mizaBorder = {
        border: '1px solid #ddd'
      }
      break;
    case ('ZASEDENO_NEPOSTREZENO'):
      stanjeMizeNapis = 'NEPOSTREŽENO';
      stanjeMizeClass = 'stanje-mize-rdeca'
      break;
    case ('ZASEDENO_POSTREZENO'):
      stanjeMizeNapis = 'POSTREŽENO';
      stanjeMizeClass = 'stanje-mize-zelena';
      mizaBorder = {
        border: '1px solid #ddd'
      }
      break;
  }

  const pozicija = sessionStorage.getItem('pozicija');

  return (
    <div className="miza-container" style={mizaBorder}>
      <div className='row'>
        <h3>Miza {data[2]}</h3>
        <h5 className={stanjeMizeClass}>{stanjeMizeNapis}</h5>
        <div className='col-lg-12'>
          {stanjeMize === 'ZASEDENO_POSTREZENO' && pozicija !== 'RECEPTOR' ? (
            <Button variant="primary" type="submit" className="miza-button" onClick={() => handleRacunClick(data[0])}>
              Račun
            </Button>
          ) : stanjeMize === 'ZASEDENO_NEPOSTREZENO' && pozicija !== 'RECEPTOR' ? (<div>
            <Button
              variant="primary"
              type="button"
              className="miza-button"
              onClick={handleNarociloClick}
            >
              Naročilo
            </Button>
            <Button
              variant="primary"
              type="button"
              className="miza-button"
              onClick={() => handlePostregelClick(data[0])}
            >
              Postregel
            </Button>
          </div>
          ) : stanjeMize === 'NEZASEDENO' && pozicija !== 'RECEPTOR' ? null : (

                <Button variant="primary" type="button" className="miza-button" onClick={handleShowModal}>
                  Stanje Mize
                </Button>

          )}
        </div>
      </div>
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Spremeni Stanje Mize</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formStanjeMize">
              <Form.Label>Izberi novo stanje:</Form.Label>
              <Dropdown onSelect={(selectedKey) => setNovoStanje(selectedKey)}>
                <Dropdown.Toggle variant="secondary" id="dropdown-basic">
                  {novoStanje || 'Izberi stanje'}
                </Dropdown.Toggle>
                <Dropdown.Menu>
                  <Dropdown.Item eventKey="NEZASEDENO">NEZASEDENO</Dropdown.Item>
                  <Dropdown.Item eventKey="ZASEDENO_NEPOSTREZENO">NEPOSTREŽENO</Dropdown.Item>

                </Dropdown.Menu>
              </Dropdown>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Prekliči
          </Button>
          <Button variant="success" onClick={() => handleStanjeChange(data[0])}>
            Spremeni
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Miza;
