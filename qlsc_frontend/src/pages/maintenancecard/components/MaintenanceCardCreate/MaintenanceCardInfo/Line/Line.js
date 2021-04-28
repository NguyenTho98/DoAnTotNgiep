import React from 'react';

import './styles.scss';

function Line(props) {
  const { name, id, value, valueHtml } = props;
  const { onChangeLine } = props;
  return (
    <div className="justify-content-between delivery-collation-line">
      <div className="line-name">
        {name}
      </div>
      <div className="line-value">
        <textarea placeholder="Ghi chú" value={value} onChange={(e) => onChangeLine(id, e)} />
      </div>
    </div>
  );
}

export default Line;
