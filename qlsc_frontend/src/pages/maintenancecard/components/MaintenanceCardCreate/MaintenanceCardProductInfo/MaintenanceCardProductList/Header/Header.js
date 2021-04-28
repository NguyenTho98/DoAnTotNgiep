import React from 'react';

function Header() {
  return (
    <div className="d-flex align-items-center delivery-collation-order-row header">
      <div className="order">
        Mã đơn hàng
      </div>
      <div className="track-code">
        Mã vận đơn
      </div>
      <div className="tenant">
        Gian hàng
      </div>
      <div className="d-flex align-items-center justify-content-end sapo-revenue">
        Doanh thu Sapo
      </div>
      <div className="d-flex align-items-center justify-content-end real-revenue">
        Doanh thu thực tế
      </div>
      <div className="d-flex align-items-center justify-content-end sapo-record">
        Phí Sapo ghi nhận
      </div>
      <div className="d-flex align-items-center justify-content-end real-record">
        Phí thực tế
      </div>
    </div>
  )
}

export default Header;
