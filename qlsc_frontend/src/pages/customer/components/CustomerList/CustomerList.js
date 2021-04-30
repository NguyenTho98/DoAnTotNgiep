/* eslint-disable no-shadow */
import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import './styles.scss';
import CustomerListBody from './CustomerListBody/CustomerListBody';
import CustomerListHeader from './CustomerHeader/CustomerListHeader';
function CustomerList(props) {
  const {  } = props;
  useEffect(() => {
  }, []);
  return (
    <div className="customer-screen-wrapper">
      <CustomerListHeader />
      <CustomerListBody/>
    </div>
  );
}
CustomerList.defaultProps = {

};

export default React.memo(connect(null, null)(CustomerList));
