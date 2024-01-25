// Narocilo.js
import React, { useState, useEffect } from 'react';
import './Narocilo.css';
import { Button, Modal, Container, Row, Col, Alert } from 'react-bootstrap';

import { useHistory } from 'react-router-dom';
import Izdelek from '../../components/Izdelek/Izdelek';


function Narocilo() {
  const [showModal, setShowModal] = useState(false);
  const [izdelki, setIzdelki] = useState([]);
  const [narocilo, setNarocilo] = useState([]);
  const [selectedIzdelek, setSelectedIzdelek] = useState('');
  const [kolicina, setKolicina] = useState(0);
  const history = useHistory();
  const [narociloOddano, setNarociloOddano] = useState(false);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/izdelki/vrniIzdelkeOrdered")
      .then(response => response.json())
      .then(data => setIzdelki(data))
      .catch(error => console.error("Error: ", error));
  }, []);

  useEffect(() => {
    if (narociloOddano) {
      const timer = setTimeout(() => {
        history.push('/home');
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [narociloOddano, history]);

  const handleNazajClick = () => {
    history.push('/home');
  };

  const handleDodajClick = () => {
    setShowModal(true);
  }

  const handleClose = () => {
    setShowModal(false);
  };

  const handleOdstrani = (indexOdstrani) => {
    setNarocilo(narocilo.filter((_, index) => index !== indexOdstrani));
  }

  const handleShraniClick = () => {
    const selectedIzdelekData = izdelki.find(item => item.id.toString() === selectedIzdelek);
    if (selectedIzdelekData) {
      const newEntry = {
        id: selectedIzdelekData.id,
        naziv: selectedIzdelekData.naziv,
        kolicina: kolicina
      };
      setNarocilo([...narocilo, newEntry]);
    }
    handleClose();
  };

  const handlePotrdiClick = () => {
    console.log(narocilo);

    const urlSegments = window.location.pathname.split('/');
    const id_miza = urlSegments[urlSegments.length - 1];

    let izdelkiIds = [];
    narocilo.forEach(izdelek => {
      for (let i = 0; i < izdelek.kolicina; i++) {
        izdelkiIds.push(izdelek.id);
      }
    });

    let cas = new Date();

    let formatiranCas = cas.getFullYear() + '-' +
    ('0' + (cas.getMonth() + 1)).slice(-2) + '-' +
    ('0' + cas.getDate()).slice(-2) + 'T' +
    ('0' + cas.getHours()).slice(-2) + ':' +
    ('0' + cas.getMinutes()).slice(-2) + ':' +
    ('0' + cas.getSeconds()).slice(-2);

    const data = {
      casRezervacije: formatiranCas,
      izdelkiIds: izdelkiIds
    };

    fetch(`http://localhost:8080/api/v1/narocila/vstaviNarocilo/${id_miza}`, { 
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data)
    })
      .then(response => response.json())
      .then(data => {
        console.log("Response from backend:", data);
        setNarociloOddano(true);
      })
      .catch(error => {
        console.error("Error while sending narocilo:", error);
      });
  }

  if (narociloOddano) {
    return (
      <Container className="mt-5">
        <Row className="justify-content-center">
          <Col md={6} className="login-container">
            <Alert variant="success">
              Naročilo oddano!
            </Alert>
          </Col>
        </Row>
      </Container>
    );
  }


  return (
    <div className="narocilo-container">
      <div className="narocilo-content" style={{ margin: '1rem' }}>
        <h2 className="narocilo-title">Naročilo</h2>
        <Button onClick={handleDodajClick} className="naprej-button col-lg-1">
          Dodaj
        </Button>
        {narocilo.map((izdelek, index) => (
          <Izdelek key={index} naziv={izdelek.naziv} kolicina={izdelek.kolicina} odstrani={() => handleOdstrani(index)} />
        ))}
      </div>
      <div className="row">
        <div className="col-lg-1">
        </div>
        <Button onClick={handleNazajClick} className="nazaj-button col-lg-1">
          Nazaj
        </Button>
        <div className="col-lg-8">
        </div>
        <Button onClick={handlePotrdiClick} className="naprej-button col-lg-1">
          Potrdi
        </Button>
        <div className="col-lg-1">
        </div>
      </div>
      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Dodaj Izdelek</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <form>
            <div className="form-group">
              <label>Ime izdelka</label>
              <select className="form-control" onChange={e => setSelectedIzdelek(e.target.value)}>
                {izdelki.map((izdelek, index) => (
                  <option key={index} value={izdelek.id}>
                    {izdelek.naziv}
                  </option>
                ))}
              </select>
            </div>
            <div className="form-group">
              <label>Količina</label>
              <input type="number" required className="form-control" placeholder="Vnesi količino" onChange={e => setKolicina(parseInt(e.target.value, 10))} />
            </div>
          </form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose} className="nazaj-button">
            Zapri
          </Button>
          <Button variant="primary" onClick={handleShraniClick} className="naprej-button">
            Shrani
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Narocilo;
