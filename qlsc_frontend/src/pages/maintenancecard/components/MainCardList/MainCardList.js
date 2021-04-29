/* eslint-disable no-shadow */
import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import './styles.scss';
import MainCardListBody from './MainCardListBody/MainCardListBody';
import MainCardListHeader from './MainCardListHeader/MainCardListHeader';
function MainCardList(props) {
  const {  } = props;
  useEffect(() => {
  }, []);
  return (
    <div className="main-card-screen-wrapper">
      <MainCardListHeader />
      <MainCardListBody/>
    </div>
  );
}
MainCardList.defaultProps = {

};

export default React.memo(connect(null, null)(MainCardList));
