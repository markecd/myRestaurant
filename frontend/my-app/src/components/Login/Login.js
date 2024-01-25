import './Login.css';
import { Form, Button, Container, Row, Col, Alert } from 'react-bootstrap';
import { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

function Login() {

  const [isLoginSuccessful, setIsLoginSuccessful] = useState(false);
  const history = useHistory();

  useEffect(() => {
    if (isLoginSuccessful) {
      const timer = setTimeout(() => {
        history.push('/home');
      }, 3000);
      return () => clearTimeout(timer);
    }
  }, [isLoginSuccessful, history]);

  function prijavi(event) {
    event.preventDefault();

    const formData = {
      username: event.target.formUsername.value,
      geslo: event.target.formPassword.value
    }

    fetch("http://localhost:8080/api/v1/natakarji/overiUporabnika", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData)
    })
      .then(response => response.json())
      .then(result => {
        console.log("prijava uspesna", result.uspesnaPrijava) //TO DO
        if (result.uspesnaPrijava) {
          setIsLoginSuccessful(true);
        }
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  }

  if (isLoginSuccessful) {
    return (
      <Container className="mt-5">
        <Row className="justify-content-center">
          <Col md={6} className="login-container">
            <Alert variant="success">
              Uspesna prijava!
            </Alert>
          </Col>
        </Row>
      </Container>
    );
  }

  return (
    <Container className="mt-5">
      <Row className="justify-content-center">
        <Col md={6} className="login-container">
          <h2 className="login-title">Login</h2>
          <Form onSubmit={prijavi}>
            <Form.Group controlId="formUsername">
              <Form.Label>Username</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter your username"
                className="mb-3"
                required
              />
            </Form.Group>

            <Form.Group controlId="formPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Enter your password"
                className="mb-3"
                required
              />
            </Form.Group>

            <Button variant="primary" type="submit" className="login-button">
              Login
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
}

export default Login;