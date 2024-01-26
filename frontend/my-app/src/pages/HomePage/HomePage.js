import Header from '../../components/Header/Header';
import Miza from '../../components/Miza/Miza';
import KanbanBoard from '../../components/Kanban/Kanban';
import React, { useState, useEffect } from 'react';
import { Redirect } from 'react-router-dom';
import './HomePage.css';

function HomePage() {
    const [mize, setMize] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/narocila/vrniZasedeneMize')
            .then(response => response.json())
            .then(data => setMize(data))
            .catch(error => console.error("Error fetching data: ", error));
    }, []);

    const pozicija = sessionStorage.getItem('pozicija');

    if (pozicija == "KUHAR") {
        return (
            <>
                <Header />
                <KanbanBoard />
            </>
        );
    }

    if (pozicija == "NATAKAR"){
        return(
            <>
                <Header></Header>
                <div className='mize-container'>
                <div className='row'>
                    {mize.map((mizaData, index) => (
                        <div key={index} className='col-lg-3'>
                            <Miza data={mizaData} /> {}
                        </div>
                    ))}
                </div>
                </div>
            </>
        )
    }
}

export default HomePage;