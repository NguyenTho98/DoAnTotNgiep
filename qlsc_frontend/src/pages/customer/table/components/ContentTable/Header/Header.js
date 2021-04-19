import React from "react";
import Checkbox from 'components/buttons/Checkbox';

function Header(props) {
  return (
    <div className="d-flex header-list customer-header">
      <div className="checkbox header-checkbox">
        <Checkbox checked onCheck/>
      </div>
      <div className="margin-right20 header-customer-id">
          Mã khách hàng
      </div>
      <div className="margin-right20 header-customer-name">
          Tên khách hàng
      </div>
      <div className="margin-right20 header-customer-created">
        Ngày tạo
      </div>
      <div className="margin-right20 header-customer-phone">
          Số điện thoại
      </div>
      <div className="margin-right20 header-customer-address">
          Địa chỉ
      </div>
    </div>
  );
}

export default Header;
