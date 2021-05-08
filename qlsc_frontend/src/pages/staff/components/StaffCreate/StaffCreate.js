/* eslint-disable no-shadow */
import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import TitleAndAction from './TitleAndAction/TitleAndAction';
import InfoStaffFooter from './InfoStaffFooter/InfoStaffFooter';
import InfoStaffLeft from './InfoStaffLeft/InfoStaffLeft';
import InfoStaffRight from './InfoStaffRight/InfoStaffRight';
import './styles.scss';
function StaffCreate(props) {
  const {  } = props;
  const [user, setUser] = useState({
    name: null,
    code: null,
    phone: null,
    email: null,
    address: null,
    city: null,
    ward: null,
    description: null
  })
  useEffect(() => {
  }, []);
  return (
    <div className="staff-screen-wrapper-create">
      <TitleAndAction />
      <div className="row">
        <div className="col-md-8">
            <InfoStaffLeft />
        </div>
        <div className="col-md-4">
            <InfoStaffRight />
        </div>
        <InfoStaffFooter />
      </div>
    </div>
  );
}
StaffCreate.defaultProps = {

};

export default React.memo(connect(null, null)(StaffCreate));
