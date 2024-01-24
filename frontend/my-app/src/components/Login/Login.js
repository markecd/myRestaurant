import './Login.css';
import { Form, Button, Container, Row, Col } from 'react-bootstrap';

function Login() {
    return (
        <Container className="mt-5">
          <Row className="justify-content-center">
            <Col md={6} className="login-container">
              <h2 className="login-title">Login</h2>
              <Form>
                <Form.Group controlId="formUsername">
                  <Form.Label>Username</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter your username"
                    className="mb-3"
                  />
                </Form.Group>
    
                <Form.Group controlId="formPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control
                    type="password"
                    placeholder="Enter your password"
                    className="mb-3"
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