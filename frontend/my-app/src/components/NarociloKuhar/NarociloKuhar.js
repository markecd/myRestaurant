import React from 'react';

const NarociloKuhar = ({ narocilo, moveForward }) => {
  return (
    <div className="narociloKuhar">
      <p>{narocilo.name}</p>
      <button onClick={() => moveForward(narocilo)}>Move Forward</button>
    </div>
  );
};

export default NarociloKuhar;
