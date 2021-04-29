import React from 'react';

function Header() {
  return (
    <div className="d-flex align-items-center delivery-collation-order-row header">
      <div className="order">
        Mã sản phẩm
      </div>
      <div className="track-code">
        Tên sản phẩm
      </div>
      <div className="tenant">
        Số lượng
      </div>
      <div className="d-flex align-items-center justify-content-end sapo-revenue">
        Bảo hành
      </div>
      <div className="d-flex align-items-center justify-content-end real-revenue">
        Giá
      </div>
      <div className="d-flex align-items-center justify-content-end sapo-record">
        Trạng thái
      </div>
    </div>
  )
}

export default Header;
