/* eslint-disable import/order */
import React from 'react';

import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Guard from 'components/general/loading/Guard';
import HeaderOrderConfirm from './HeaderOrderConfirm';
import ListOrderConfirm from './ListOrderConfirm';
import './styles.scss';
import * as Icons from 'components/sapo/deliveryCollation/commons/Icons';

function ConfirmDeliveryCollation(props) {
  const {
    totalShipFee,
    totalAmount,
    showModal,
    setShowModal,
    setPaid,
    paid,
    createDeliveryCollation,
    payment,
    paymentItem,
    setPaymentItem,
  } = props;
  const handleClose = () => {
    setShowModal(false);
  };
  const renderPaymentLabel = () => {
    if (!paymentItem && payment.length > 0) {
      return payment[0].name;
    }
    if (payment && payment.length > 0) {
      const selected = payment.find((item) => item.id === paymentItem.id);
      if (selected) {
        return selected.name;
      }
    }
    return 'Không có phương thức thanh toán nào';
  };
  const show = showModal;
  return (
    <Modal
      show={show}
      onHide={handleClose}
      size="lg"
      dialogClassName="modal-delivery-collation-data"
    >
      <Modal.Header closeButton>
        <div className="modal-title">
          <span>
             Xác nhận thông tin đối soát
          </span>
        </div>
      </Modal.Header>
      <Modal.Body>
        <div className="content">
          <HeaderOrderConfirm />
          <ListOrderConfirm totalShipFee={totalShipFee} totalAmount={totalAmount} />
        </div>
        <div className="confim">
          <div style={{ height: '70px' }}>
            {
            paid ? (
              <div className="filter-delivery-collations-option">
                <div className="title-option">
                  Lựa chọn phương thức thanh toán cho đơn đối soát
                </div>
                <button
                  type="button"
                  className="dropdown-toggle delivery-collations-dropdown-button"
                  data-toggle="dropdown"
                >
                  {renderPaymentLabel()}
                  <Icons.Arrow />
                </button>
                <div className="dropdown-menu delivery-collations-dropdown-menu">
                  {payment.map((item, index) => {
                    return (
                      <a
                        className="dropdown-item delivery-collations-dropdown-item"
                        key={index}
                        href
                        onClick={() => setPaymentItem(item)}
                      >
                        {item.name}
                      </a>

                    );
                  })}
                </div>
              </div>
            ) : ''
          }
          </div>
          <div className="check-confirm-payment">
            <div className="d-flex right">
              <Icons.Confirm />
              <span className="label">Xác nhận thanh toán</span>
            </div>
            <div role="presentation" className="checkbox header-checkbox left" onClick={() => setPaid(!paid)}>
              <input
                type="checkbox"
                name="check"
                checked={paid}
                readOnly
              />
              <label style={{
                left: '-8.5px',
                top: '-1px'
              }}
              />
            </div>
          </div>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <React.Fragment>
          <Button variant="light" onClick={handleClose}>
            Thoát
          </Button>
          <Button variant="primary" onClick={() => {
            createDeliveryCollation();
            handleClose();
          }}
          >
            Xác nhận
          </Button>
        </React.Fragment>
      </Modal.Footer>
    </Modal>
  );
}

export default ConfirmDeliveryCollation;
