/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import './styles.scss';
import { connect } from 'react-redux';

function MainCardCreate(props) {
  return (
    <div className="main-card-create-warpper">
        <div className="contatiner">
            <div className="row">
                <div className="col-md-8">

                </div>
                <div className="col-md-4">

                </div>
            </div>
        </div>
    </div>
  );
}
MainCardCreate.defaultProps = {

};
export default React.memo(connect(null, null)(MainCardCreate));
