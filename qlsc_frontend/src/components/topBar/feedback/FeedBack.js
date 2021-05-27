/* eslint-disable no-shadow */
import React, { useEffect, useState } from 'react';
import './styles.scss';
import axios from 'axios';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import { connect } from 'react-redux';
import Guard from 'components/Guard/Guard';
import * as Icons from '../Icons';
import { changeShowFeedBack } from '../../../actions/globalUiActions';

function Feedback(props) {
  const { showFeedback, changeShowFeedBack, user } = props;
  const [content, setContent] = useState('');
  const [fetching, setFetching] = useState(false);
  const [status, setStatus] = useState(1);
  const handleClose = () => {
    setContent('');
    setFetching(false);
    setStatus(1);
    changeShowFeedBack(false);
  };
  const onConfirm = () => {
    setFetching(true);
    sendData();
  };
  const sendData = () => {
    if (content.length === 0) {
      SapoApp.flashError('Bạn cần nhập nội dung góp ý');
    } else {
      axios({
        method: 'get',
        url: 'https://v1.nocodeapi.com/nguyentho/google_sheets/wYYVCvVkUPMkoSVP?tabId=feedback',

      }).then((json) => {
        let data = {};
        data = {
          stt: json.data.total + 1,
          site: "Site-test",
          email: user.email,
          content,
          time: new Date().toLocaleTimeString('vi-VN', {
            hour12: false,
            day: 'numeric',
            month: 'numeric',
            year: 'numeric',
            hour: 'numeric',
            minute: 'numeric',
          }),
        };
        try {
          fetch(
            'https://v1.nocodeapi.com/nguyentho/google_sheets/wYYVCvVkUPMkoSVP?tabId=feedback',
            {
              method: 'post',
              body: JSON.stringify([
                [data.stt, data.site, data.email, data.content, data.time],
              ]),
              headers: {
                'Content-Type': 'application/json',
              },
            },
          ).then((json) => {
            if (json && json.status == 200) {
              setFetching(false);
              setStatus(2);
            }
          });
        } catch (error) {
          console.error('Error:', error);
        }
      });
    }
  };
  const thankYou = () => {
    return (
      <React.Fragment>
        <div className="icon-thank-you">
          <Icons.ThankYou />
        </div>
        <div className="text" style={{ textAlign: 'center'}}>Cảm ơn bạn đã đóng góp ý kiến cho Kiomo! </div>
      </React.Fragment>
    );
  };
  return (
    <Modal
      show={showFeedback}
      onHide={handleClose}
      size="md"
      dialogClassName="modal-feed-back"
    >
      <Modal.Header closeButton>
        <div className="modal-title">
          <span>Gửi góp ý</span>
        </div>
      </Modal.Header>
      <Modal.Body>
        <div className="content">
          {
            fetching ? (
              <div>
                <Guard />
              </div>
            )
              : status == 1 ? (
                <textarea placeholder="Hãy gửi góp ý của bạn cho chúng tôi tại đây nhé." value={content} onChange={(e) => setContent(e.target.value)} />
              ) : thankYou()

          }
        </div>
      </Modal.Body>
      <Modal.Footer>
        <React.Fragment>
          {
            status == 1 ? (
              <React.Fragment>
                <Button variant="light" onClick={handleClose} disabled={fetching}>
                  Hủy
                </Button>
                <Button variant="primary" onClick={onConfirm} disabled={fetching}>
                  Gửi
                </Button>
              </React.Fragment>
            ) : (
              <Button variant="light" onClick={handleClose}>
                Thoát
              </Button>
            )
          }
        </React.Fragment>
      </Modal.Footer>
    </Modal>
  );
}
Feedback.defaultProps = {};

const mapStateToProps = (state, ownProps) => {
  const { globalUI: { showFeedback }, auth: { user }} = state;
  return {
    showFeedback,
    user,
  }
}
const mapDispatchToProps = (dispatch) => ({
  changeShowFeedBack: (show) => dispatch(changeShowFeedBack(show))
});
export default connect(mapStateToProps, mapDispatchToProps, null)(Feedback);
