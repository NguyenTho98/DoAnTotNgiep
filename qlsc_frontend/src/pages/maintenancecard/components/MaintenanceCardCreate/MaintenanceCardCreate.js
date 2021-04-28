/* eslint-disable consistent-return */
/* eslint-disable no-fallthrough */
/* eslint-disable jsx-a11y/click-events-have-key-events */
import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { useHistory } from 'react-router';
import pushstate from 'util/pushstate';
import callApi from '../../../../../common/callApi';
import { fetchOrderCollation, resetFilter } from '../../actions/deliveryCollation';

import * as Icons from '../../commons/Icons';
import DeliveryCollationInfo from './DeliveryCollationInfo/DeliveryCollationInfo';
import DeliveryCollationOrderInfo from './DeliveryCollationOrderInfo/DeliveryCollationOrderInfo';
import ConfirmDeliveryCollation from './DeliveryCollationOrderInfo/DeliveryCollationOrderList/Modal/ConfirmDeliveryCollation';
import ConfirmLocationModal from './DeliveryCollationOrderInfo/DeliveryCollationOrderList/Modal/ConfirmLocationModal';

import './styles.scss';

function MaintenanceCardCreate(props) {
  const history = useHistory();
  const {
    locations, listOrderCollation, resetFilter, filterInfo
  } = props;
  const tenant = JSON.parse(sessionStorage.getItem('tenant')) || {};
  const arrLocations = Object.values(locations);
  const [locationId, setLocationId] = useState('');
  const [tags, setTags] = useState([]);
  const [note, setNote] = useState('');
  const [showLocationModal, setShowLocationModal] = useState(false);
  const [tmpLocation, setTmpLocation] = useState(null);
  const [paid, setPaid] = useState(false);
  const [showModal, setShowModal] = useState(false);
  const [payment, setPayment] = useState([]);
  const [paymentItem, setPaymentItem] = useState('');
  const [store, setStore] = useState(1);

  useEffect(() => {
    const { pathname } = window.location;
    const storeTmp = pathname.split('/home/delivery-collation/create/');
    if (storeTmp[1]) {
      switch (storeTmp[1]) {
        case 'shopee':
          setStore(1);
          return;
        case 'sendo':
          setStore(3);
          return;
        case 'lazada':
          setStore(2);
          return;
        case 'tiki':
          setStore(4);
          return;
        default:
          setStore(1);
      }
    }
  }, []);
  useEffect(() => {
    const url = '/api/payment-methods';
    const options = {
      method: 'GET',
    };
    callApi(url, options).then(json => {
      if (json && json.data) {
        setPayment(json.data.payment_methods);
      }
    });
  }, []);
  useEffect(() => {
    const selectedLocationTmp = [];
    if (!locationId) {
      const url = '/settings/locations';
      const options = {
        method: 'GET',
      };
      const { pathname } = window.location;
      const storeTmp = pathname.split('/home/delivery-collation/create/');
      callApi(url, options).then(json => {
        if (json && json.data && json.data.length > 0) {
          selectedLocationTmp.push(json.data[0].id);
          setLocationId(json.data[0].id);
          const selectedStore = [];
          if (tenant && tenant.connections && tenant.connections.length > 0) {
            for (let i = 0; i < tenant.connections.length; i++) {
              if (tenant.connections[i].channel_type === storeTmp[1]) {
                selectedStore.push(tenant.connections[i].id);
              }
            }
          }
          filterInfo.selectedStore = selectedStore;
          filterInfo.selectedLocation = selectedLocationTmp;
          filterInfo.status = 1;
          props.fetchOrderCollation(filterInfo);
        }
      });
    }
    if (listOrderCollation.length > 0) {
      const tmp = listOrderCollation[0];
      setLocationId(tmp.sapo_location_id);
    }
  }, [locationId]);
  const onBack = () => {
    resetFilter();
    pushstate(history, '/home/delivery-collations');
  };
  const totalAmount = () => {
    let total = 0;
    if (listOrderCollation.length === 0) {
      return total;
    }
    listOrderCollation.forEach(element => {
      if (element.service_provider_cod_amount) {
        total += element.service_provider_cod_amount;
      }
    });
    return total;
  };
  const totalShipFee = () => {
    let total = 0;
    if (listOrderCollation.length === 0) {
      return total;
    }
    listOrderCollation.forEach(element => {
      if (element.service_provider_ship_fee) {
        total += element.service_provider_ship_fee;
      }
    });
    return total;
  };
  const total1 = totalAmount();
  const total2 = totalShipFee();
  const total3 = totalAmount() - totalShipFee();
  const onChangeLocationId = (id) => {
    setLocationId(id);
  };

  const onSelectLoaction = (a) => {
    if (!locationId) {
      const tmp = arrLocations[0].id;
      if (a !== tmp) {
        setTmpLocation(a);
        setShowLocationModal(true);
      }
    } else if (a !== locationId) {
      setTmpLocation(a);
      setShowLocationModal(true);
    }
  };

  const createDeliveryCollation = (e) => {
    if (listOrderCollation.length === 0) {
      SapoApp.flashError('Chưa có đơn hàng nào trong phiếu đối soát');
      return;
    }
    const formData = {
      account_id: SapoApp.User.current.id,
      location_id: locationId || arrLocations[0].id,
      delivery_service_provider_id: listOrderCollation[0].sapo_delivery_service_provider_id || undefined,
      note: note || undefined,
      tags: tags || undefined,
      line_items: listOrderCollation,
      paid,
      payment_method_id: paymentItem ? paymentItem.id : undefined,
      amount: total3,
    };
    const url = '/api/delivery-collations';
    const options = {
      method: 'POST',
      data: JSON.stringify(formData),
    };
    return callApi(url, options).then((response) => {
      if (response.data && response.data.success) {
        pushstate(history, `/home/delivery-collation/${response.data.id}`);
      } else if (response.data && response.data.message) {
        SapoApp.flashError(response.data.message);
      } else {
        SapoApp.flashError('Có lỗi xảy ra khi tạo phiếu đối soát');
      }
    }).catch((req) => {
      console.log('req', req);
    });
  };

  const onChangeLine = (id, e) => {
    const { value } = e.target;
    if (value.length > 255) {
      SapoApp.flashError('Ghi chú không được quá 255 ký tự.');
    } else {
      setNote(value);
    }
  };


  const addTag = (val) => {
    let count = 0;
    tags.forEach((tag) => {
      count += tag.length;
    });
    if (val && val.length > 100) {
      SapoApp.flashError('Tag không quá 100 ký tự');
      return;
    }
    if (val && (val.length + count) > 500) {
      SapoApp.flashError('Tag không được quá 500 ký tự.');
      return;
    }
    setTags(tags.concat(val));
  };

  const handleClose = () => {
    setTags([]);
  };

  const deleteTag = (index, name) => {
    setTags(tags.filter((item, id) => id !== index));
  };

  const onChange = (e) => {
    if (e.target.value.trim().length > 1) {
      setTags(e.target.value.trim());
      e.persist();
    }
    setTags(e.target.value);
  };
  return (
    <div
      className="delivery-collation-create-wrapper"
    >
      <div className="go-back" onClick={onBack}>
        <Icons.arrowLeft />
        <div>
          <span>
            Quay lại&nbsp;
          </span>
          <span>
            Đối soát
          </span>
        </div>
      </div>
      {/* <div className="delivery-collation-create-header">
        <span>Tạo phiếu đối soát</span>
      </div> */}
      <DeliveryCollationInfo
        locationId={locationId}
        tags={tags}
        note={note}
        store={store}
        addTag={addTag}
        deleteTag={deleteTag}
        handleClose={handleClose}
        onChange={onChange}
        arrLocations={arrLocations}
        onSelectLoaction={(id) => onSelectLoaction(id)}
        onChangeLine={(id, e) => onChangeLine(id, e)}
        channelType={filterInfo.channelType}
        listOrderCollation={listOrderCollation}
      />
      <DeliveryCollationOrderInfo
        totalAmount={total1}
        totalShipFee={total2}
        total={total3}
        channelType={filterInfo.channelType}
        setShowModal={(show) => setShowModal(show)}
        listOrderCollation={listOrderCollation}
        arrLocations={arrLocations}
        locationId={locationId}
        store={store}
      />
      <ConfirmLocationModal
        show={showLocationModal}
        setShowLocationModal={setShowLocationModal}
        tmpLocation={tmpLocation}
        onChangeLocationId={(id) => onChangeLocationId(id)}
      />
      <ConfirmDeliveryCollation
        totalAmount={total1}
        totalShipFee={total2}
        showModal={showModal}
        setPaid={setPaid}
        paid={paid}
        setShowModal={(show) => setShowModal(show)}
        payment={payment}
        paymentItem={paymentItem}
        setPaymentItem={setPaymentItem}
        createDeliveryCollation={() => createDeliveryCollation()}
      />
    </div>
  );
}
const mapStateToProps = (state) => {
  const {
    locations,
    deliveryCollation: { orderCollations: { listOrderCollation }, filterInfo }
  } = state;
  return {
    locations,
    listOrderCollation,
    filterInfo,
  };
};
const mapDispatchToProps = (dispatch, ownProps) => ({
  resetFilter: () => dispatch(resetFilter()),
  fetchOrderCollation: (filterInfo) => dispatch(fetchOrderCollation(filterInfo)),
});
export default connect(null, null)(MaintenanceCardCreate);
