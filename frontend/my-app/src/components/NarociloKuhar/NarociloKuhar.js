import './NarociloKuhar.css';
import React from 'react';

const NarociloKuhar = ({ narocilo, moveForward }) => {
  return (
    <div className="narociloKuhar">
      <p><strong>Naročilo {narocilo.id}</strong></p>
      {narocilo.izdelki.map(([izdelek, quantity], index) => (
        <p key={index}>{izdelek.naziv} - Količina: {quantity}</p>
      ))}
      <button onClick={() => moveForward(narocilo)}>NAPREJ</button>
    </div>
  );
};

export default NarociloKuhar;
