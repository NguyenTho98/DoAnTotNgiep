import React, { useEffect, useState } from "react";
import { connect } from "react-redux";
import InfoCustomerLeft from "./InfoCustomerLeft/InfoCustomerLeft";
import InfoCustomerRight from "./InfoCustomerRight/InfoCustomerRight";
import { saveCustomer } from "../../actions/customerAction";
import { receiveWard } from "../../actions/locationActions";
import pushstate from "utils/pushstate";
import "./styles.scss";

const initialState = {
  name: null,
  code: null,
  phone: null,
  email: null,
  address: null,
  city: null,
  ward: null,
  description: null,
};
function CustomerCreate(props) {
  const { onClearWards, onSaveCustomer, positionCallApi } = props;
  const [user, setUser] = useState(initialState);

  useEffect(() => {
    if (positionCallApi) setUser({ ...user, ward: null });
  }, [positionCallApi]);

  const onChangeUser = (type, value) => {
    setUser(() => {
      return {
        ...user,
        [type]: value,
      };
    });
  };

  const saveCustomer = () => {
    onSaveCustomer(user).then((json) => {
      if (json && json.success) {
        setUser(initialState);
        onClearWards();
        pushstate(props.history, "/customer");
      }
    });
  };
  return (
    <div className="customer-screen-wrapper-create">
      <div className="row">
        <div className="col-md-8">
          <InfoCustomerLeft onChangeUser={onChangeUser} user={user} />
        </div>
        <div className="col-md-4">
          <InfoCustomerRight onChangeUser={onChangeUser} user={user} />
        </div>
      </div>
    </div>
  );
}
CustomerCreate.defaultProps = {};

const mapStateToProps = (state) => {
  const {
    locations: { positionCallApi },
  } = state;
  return {
    positionCallApi,
  };
};
const mapDispatchToProps = (dispatch) => ({
  onSaveCustomer: (customer) => dispatch(saveCustomer(customer)),
  onClearWards: () => dispatch(receiveWard([])),
});

export default React.memo(connect(mapStateToProps, mapDispatchToProps)(CustomerCreate));
