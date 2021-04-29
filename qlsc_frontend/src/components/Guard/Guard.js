import React from 'react';
import './guard.scss';

export default ({ backgroundColor, marginTop = '10%' }) => (
  <div className="content"
    style={{
      display: 'table',
      margin: '0 auto',
      marginTop: { marginTop },
    }}
  >

    <div className="load-wrapp" style={{ backgroundColor }}>
      <div className="load-9">
        <div className="spinner">
          <div className="bubble-1" />
          <div className="bubble-2" />
        </div>
      </div>
    </div>

  </div>
);
