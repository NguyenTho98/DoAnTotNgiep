/* eslint-disable no-shadow */
import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import './styles.scss';
import StaffListBody from './StaffListBody/StaffListBody';
import StaffListHeader from './StaffListHeader/StaffListHeader';
function StaffList(props) {
  const {  } = props;
  useEffect(() => {
  }, []);
  return (
    <div className="staff-screen-wrapper">
      <StaffListHeader />
      <StaffListBody/>
    </div>
  );
}
StaffList.defaultProps = {

};

export default React.memo(connect(null, null)(StaffList));
