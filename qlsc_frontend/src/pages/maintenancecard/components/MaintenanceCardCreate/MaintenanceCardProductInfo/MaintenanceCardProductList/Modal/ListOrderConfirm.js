import React from 'react';
import { moneyFormat } from 'util/moneyFormat';

function ListOrderConfirm(props) {
  const { totalShipFee, totalAmount } = props;
  return (
    <div className="d-flex align-items-center delivery-collation-order-row">
      <div className="total-amount">
        {moneyFormat(totalAmount)}đ
      </div>
      <div className="total-ship-fee">
        {moneyFormat(totalShipFee)}đ
      </div>
      <div className="category">
        { (totalAmount - totalShipFee) > 0 ? 'Phiếu thu' : 'Phiếu chi'}
      </div>
      <div className="d-flex align-items-center justify-content-end total-delivery-collation">
        {moneyFormat(totalAmount - totalShipFee)}đ
      </div>
    </div>
  );
}

export default ListOrderConfirm;
