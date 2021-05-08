/* eslint-disable no-shadow */
import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import TitleAndAction from "./TitleAndAction/TitleAndAction";
import InfoProductFooter from "./InfoProductFooter/InfoProductFooter";
import InfoProductLeft from "./InfoProductLeft/InfoProductLeft";
import InfoProductRight from "./InfoProductRight/InfoProductRight";
import "./styles.scss";
function ProductCreate(props) {
  const {} = props;
  const [user, setUser] = useState({
    name: null,
    code: null,
    phone: null,
    email: null,
    address: null,
    city: null,
    ward: null,
    description: null,
  });
  useEffect(() => {}, []);
  return (
    <React.Fragment>

      <div className="product-screen-wrapper-create">
      <TitleAndAction />
        <div className="row">
          <div className="col-md-8">
            <InfoProductLeft />
          </div>
          <div className="col-md-4">
            <InfoProductRight />
          </div>
          <InfoProductFooter />
        </div>
      </div>
    </React.Fragment>
  );
}
ProductCreate.defaultProps = {};

export default React.memo(connect(null, null)(ProductCreate));
