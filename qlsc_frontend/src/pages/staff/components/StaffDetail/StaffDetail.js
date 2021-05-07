/* eslint-disable no-shadow */
import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { withRouter } from 'react-router';
import StaffDetailInfo from './StaffDetailInfo/StaffDetailInfo';
import HistoryMainCard from './HistoryMainCard/HistoryMainCard';
import './styles.scss';
function StaffDetail(props) {
  const {  } = props;
  useEffect(() => {
  }, []);
  return (
    <div className="staff-screen-wrapper-detail">
        <StaffDetailInfo />
        <HistoryMainCard />
    </div>
  );
}
StaffDetail.defaultProps = {

};

export default withRouter(StaffDetail);
