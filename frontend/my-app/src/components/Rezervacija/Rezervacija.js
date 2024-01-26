import React, { useState } from 'react';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';
import './Rezervacija.css'; // Import your Reservation.css styles

function Rezervacija() {
    const [formData, setFormData] = useState({
        ime_priimek: '',
        cas_rezervacije: '',
        id_miza: '',
    });

    const [showForm, setShowForm] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value,
        });
    };

    const handleCreateReservationClick = () => {
        setShowForm(true);
    };

    const handleCancelClick = () => {
        setShowForm(false);
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        fetch('http://localhost:8080/api/v1/rezervacije/vstaviRezervacijo/' + formData.id_miza, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log('Reservation created:', data);
                setShowForm(false); // Close the form after successful submission
                // Optionally, you can redirect the user or perform other actions
            })
            .catch((error) => console.error('Error creating reservation:', error));
    };

    return (
        <>
            {!showForm && (
                <div className="text-center">
                    <Button
                        className="reservation-button"
                        variant="primary"
                        onClick={handleCreateReservationClick}
                    >
                        Ustvari rezervacijo
                    </Button>
                </div>
            )}

            {showForm && (
                <Container className="reservation-container">
                    <Row className="justify-content-center">
                        <Col md={6} className="reservation-content">
                            <h2 className="reservation-title">Rezervacija</h2>
                            <Form onSubmit={handleSubmit}>
                                <Form.Group controlId="ime_priimek">
                                    <Form.Label>Ime in priimek</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vnesite podatke stranke"
                                        name="ime_priimek"
                                        value={formData.ime_priimek}
                                        onChange={handleChange}
                                    />
                                </Form.Group>

                                <Form.Group controlId="cas_rezervacije">
                                    <Form.Label>Čas rezervacije</Form.Label>
                                    <Form.Control
                                        type="datetime-local"
                                        name="cas_rezervacije"
                                        value={formData.cas_rezervacije}
                                        onChange={handleChange}
                                    />
                                </Form.Group>

                                <Form.Group controlId="id_miza">
                                    <Form.Label>Številka mize</Form.Label>
                                    <Form.Control
                                        type="text"
                                        placeholder="Vnesite številko mize"
                                        name="id_miza"
                                        value={formData.id_miza}
                                        onChange={handleChange}
                                    />
                                </Form.Group>

                                <div className="d-flex justify-content-between">
                                    <Button className="reservation-button" variant="primary" type="submit">
                                        Ustvari
                                    </Button>
                                    <Button onClick={handleCancelClick} className="reservation-button">
                                        Prekliči
                                    </Button>
                                </div>
                            </Form>
                        </Col>
                    </Row>
                </Container>
            )}
        </>
    );
}

export default Rezervacija;
