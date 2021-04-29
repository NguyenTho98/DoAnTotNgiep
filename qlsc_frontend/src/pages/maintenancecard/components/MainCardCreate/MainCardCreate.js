/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import './styles.scss';
import { connect } from 'react-redux';
import ProductMainCard from './ProductMainCard/ProductMainCard';
import InfoCustomer from './InfoCustomer/InfoCustomer';
import InfoMainCard from './InfoMainCard/InfoMainCard';

function MainCardCreate(props) {
  return (
    <div className="main-card-create-warpper">
        <div className="contatiner">
            <div className="row">
                <div className="col-md-8">
                  <InfoCustomer />
                  <ProductMainCard />
                </div>
                <div className="col-md-4">
                  <InfoMainCard />
                </div>
            </div>
        </div>
    </div>
  );
}
MainCardCreate.defaultProps = {

};
export default React.memo(connect(null, null)(MainCardCreate));
