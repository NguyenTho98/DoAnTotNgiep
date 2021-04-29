import React from 'react';
import SearchCustomer from './SearchCustomer/SearchCustomer';

import './styles.scss';

function InfoCustomer(props) {
  return (
    <div className="info-customer-warpper">
      <div className="title">
        Thông tin khách hàng
      </div>
      <SearchCustomer />
    </div>
  );
}

export default InfoCustomer;
