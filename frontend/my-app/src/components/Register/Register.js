import React, { useState, useEffect } from 'react';
import './Register.css';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';

function Register() {
    const [pozicije, nastaviPozicije] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/natakarji/vrniPozicije')
            .then(response => response.json())
            .then(pozicije => {
                console.log(pozicije);
                nastaviPozicije(pozicije)
            })
            .catch(error => console.error('Napaka pri pridobivanju pozicij:', error))
    }, []);

    function registriraj(event){
        event.preventDefault();

        const formData = {
            ime: event.target.formIme.value,
            priimek: event.target.formPriimek.value,
            username: event.target.formUsername.value,
            geslo: event.target.formPassword.value,
            pozicija: event.target.formPozicija.value,
        };

        fetch("http://localhost:8080/api/v1/natakarji/vstaviNatakarja", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Deluje: ', data)
            window.location.href = '/';
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    }

    return (
        <Container className="mt-5">
            <Row className="justify-content-center">
                <Col md={6} className="register-container">
                    <h2 className="register-title">Register</h2>
                    <Form onSubmit={registriraj}>
                        <Form.Group controlId="formIme">
                            <Form.Label>Ime</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter your name"
                                className="mb-3"
                            />
                        </Form.Group>

                        <Form.Group controlId="formPriimek">
                            <Form.Label>Priimek</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter your surname"
                                className="mb-3"
                            />
                        </Form.Group>

                        <Form.Group controlId="formUsername">
                            <Form.Label>Uporabniško ime</Form.Label>
                            <Form.Control
                                type="text"
                                placeholder="Enter your username"
                                className="mb-3"
                            />
                        </Form.Group>

                        <Form.Group controlId="formPassword">
                            <Form.Label>Geslo</Form.Label>
                            <Form.Control
                                type="password"
                                placeholder="Enter your password"
                                className="mb-3"
                            />
                        </Form.Group>

                        <Form.Group controlId="formPozicija">
                            <Form.Label>Pozicija</Form.Label>
                            <Form.Control as="select" className="mb-3">
                                <option value="" disabled selected hidden>Izberi pozicijo</option>

                                {pozicije.map((pozicija, index) => (
                                    <option key={index} value={pozicija}>{pozicija}</option>
                                ))}
                            </Form.Control>

                        </Form.Group>

                        <Button variant="primary" type="submit" className="register-button">
                            Register
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
}

export default Register;