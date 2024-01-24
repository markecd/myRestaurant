import Header from '../../components/Header/Header';
import Miza from '../../components/Miza/Miza';
import './HomePage.css';

function HomePage() {
    return(
        <>
            <Header></Header>
            <div className='mize-container'>
                <div className='col-lg-4'>
                    <Miza></Miza>
                </div>
            </div>
        </>
    )
}

export default HomePage;