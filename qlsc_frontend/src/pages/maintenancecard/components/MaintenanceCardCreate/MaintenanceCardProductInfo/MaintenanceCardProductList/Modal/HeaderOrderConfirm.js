import React from 'react';

function HeaderOrderConfirm() {
  return (
    <div className="d-flex align-items-center delivery-collation-order-row header">
      <div className="total-amount">
        Tổng giá trị đơn hàng
      </div>
      <div className="total-ship-fee">
        Tổng phí phải chi
      </div>
      <div className="category">
        Loại phiếu
      </div>
      <div className="d-flex align-items-center justify-content-end total-delivery-collation">
        Tổng doanh thu đối soát
      </div>
    </div>
  )
}

export default HeaderOrderConfirm;
