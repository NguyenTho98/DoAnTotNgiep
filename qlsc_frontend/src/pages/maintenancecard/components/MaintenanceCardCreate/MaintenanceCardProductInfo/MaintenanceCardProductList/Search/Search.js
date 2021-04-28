/* eslint-disable jsx-a11y/click-events-have-key-events */
/* eslint-disable consistent-return */
import React, { useState, useRef, useEffect } from 'react';
import Input from 'components/general/input/Input';
import debounce from 'util/debounce';
import './styles.scss';
import { connect } from 'react-redux';
import callApi from 'common/callApi';
import {
  addItemOrderCollation, addItemOrderCollationImportFile, fetchFilterOrderCollation, fetchOrderCollation, updateFetchingDeliveryCollation
} from '../../../../../actions/deliveryCollation';
import OrderListSearch from './OrderListSearch/OrderListSearch';

let timeOut;
function Search(props) {
  const [show, setShow] = useState(false);
  const [search, setSearch] = useState('');
  const [focus, setFocus] = useState(false);
  const [fetching, setFetching] = useState(false);
  const [page, setPage] = useState(1);
  const [total, setTotal] = useState(0);
  const [list, setList] = useState([]);
  const [metaData, setMetaData] = useState({});
  const [textSearch, setTextSearch] = useState('');
  const tenant = JSON.parse(sessionStorage.getItem('tenant')) || {};
  useEffect(() => {
    return () => {
      clearTimeout(timeOut);
    };
  }, []);

  // const search = (e) => {
  //   const { filterInfo, locationId, store } = props;
  //   const selectedStore = [];
  //   const selectedLocation = [];
  //   if (tenant && tenant.connections && tenant.connections.length > 0) {
  //     for (let i = 0; i < tenant.connections.length; i++) {
  //       if (tenant.connections[i].channel_type === store) {
  //         selectedStore.push(tenant.connections[i].id);
  //       }
  //     }
  //   }
  //   selectedLocation.push(locationId);
  //   filterInfo.filterText = e;
  //   filterInfo.selectedStore = selectedStore;
  //   filterInfo.selectedLocation = selectedLocation;
  //   props.fetchOrderCollation(filterInfo);
  // };

  // const searchChange = debounce((e) => search(e), 500);

  const onChangeText = (e) => {
    e.persist();
    setSearch(e.target.value);
    onSearch(e.target.value);
  };
  const inputRef = useRef();
  // const onFocus = () => {
  //   props.updateFetchingDeliveryCollation(true);
  //   searchChange(textSearch);
  //   setShow(true);
  // };
  // const onBlur = () => {
  //   timeOut = setTimeout(() => {
  //     setShow(false);
  //   }, 50);
  // };
  const onOpenFile = () => {
    if (inputRef) inputRef.current.click();
  };
  const checkFile = (data) => {
    const validExts = new Array('.xlsx', '.xls', '.csv');
    const fileExt = data.name.substring(data.name.lastIndexOf('.'));
    if (validExts.indexOf(fileExt) < 0) {
      return false;
    } return true;
  };
  const uploadFile = (e) => {
    const { locationId, store } = props;
    const { target } = e;
    const uploadedFiles = Array.from(target.files);
    if (!checkFile(uploadedFiles[0])) {
      SapoApp.flashError('Vui lòng chọn đúng định dạng file Excel');
      return;
    }
    const formData = new FormData();
    formData.append('file', uploadedFiles[0]);

    if (!store) {
      SapoApp.flashError('Vui lòng chọn sàn thương mại điện tử');
      return;
    }
    const selectedStore = [];
    if (tenant && tenant.connections && tenant.connections.length > 0) {
      for (let i = 0; i < tenant.connections.length; i++) {
        if (tenant.connections[i].channel_type === store) {
          selectedStore.push(tenant.connections[i].id);
        }
      }
    }
    if (!locationId) {
      SapoApp.flashError('Vui lòng chọn chi nhánh');
      return;
    }
    const url = `/api/order-collations/import?type=${store}&locationId=${locationId}&ids=${selectedStore}`;
    const options = {
      method: 'POST',
      'Content-Type': '',
      data: formData,
    };
    e.target.value = '';
    return callApi(url, options).then((response) => {
      if (response.data && response.data.length === 0) {
        SapoApp.flashError('Không có đơn');
      }
      props.addItemOrderCollationImportFile(response.data);
    }).catch(() => {
      SapoApp.flashError('Xảy ra lỗi khi lấy đơn hàng từ file excel');
    });
  };

  const debounceScroll = debounce((e) => {
    onScroll(e, false);
  }, 200);

  const onSearch = (value) => {
    setFetching(true);
    setList([]);
    setMetaData({});
    setSearch(value);
    debounceSearch(value, true, 1);
  };

  const onFocus = () => {
    setFetching(true);
    getOrderCollation(search || '', undefined, 1);
    setFocus(true);
  };

  const onBlur = () => {
    setTimeout(() => {
      setList([]);
      setMetaData({});
      setFocus(false);
      setFetching(true);
    }, 0);
  };

  const debounceSearch = debounce((value, reset, p) => {
    getOrderCollation(value, reset, p);
  }, 400);

  const getOrderCollation = (s, reset, p) => {
    const { filterInfo, locationId, store } = props;
    const selectedStore = [];
    const selectedLocation = [];
    if (tenant && tenant.connections && tenant.connections.length > 0) {
      for (let i = 0; i < tenant.connections.length; i++) {
        if (tenant.connections[i].channel_type === store) {
          selectedStore.push(tenant.connections[i].id);
        }
      }
    }
    selectedLocation.push(locationId);
    filterInfo.filterText = s;
    filterInfo.selectedStore = selectedStore;
    filterInfo.selectedLocation = selectedLocation;
    props.fetchFilterOrderCollation(filterInfo, p).then(json => {
      setFetching(false);
      if (json && json.channel_order_collation && json.metadata) {
        const newOrder = json.channel_order_collation;
        setMetaData(json.metadata);
        if (reset) {
          setList([...newOrder]);
        } else {
          setList(list.concat([...newOrder]));
        }
      }
    });
  };

  const onScroll = (e) => {
    if (e.target.scrollHeight - e.target.scrollTop - e.target.clientHeight <= 200) {
      if (metaData.page < metaData.total) {
        getOrderCollation(search, false, metaData.page + 1);
      }
    }
  };

  const renderSearch = () => {
    return (
      <div className="position-absolute order-list-search-wrapper">
        <div className="d-flex align-items-center head">
          <div className="order-code">
          Mã đơn hàng
          </div>
          <div className="tracking-code">
          Mã vận đơn
          </div>
          <div className="tenant">
          Gian hàng
          </div>
          <div className="order-total-amount">
          Giá trị đơn hàng
          </div>
          <div className="order-ship-fee">
          Phí đơn hàng
          </div>
        </div>
        <div className="list-item-search"
          onScroll={e => {
            e.persist();
            e.stopPropagation();
            debounceScroll(e);
          }}
        >
          <OrderListSearch
            onClick={(item) => {
              props.addItemOrderCollation(item);
              setShow(false);
              setTextSearch('');
            }}
            list={list}
            fetching={fetching}
          />
        </div>
      </div>
    );
  };

  return (
    <div className="d-flex align-items-center justify-content-between position-relative search-order-container">
      <div className="position-relative search-wrapper">
        <Input
          onChange={(e) => onChangeText(e)}
          onFocus={onFocus}
          onBlur={onBlur}
          placeHolder="Tìm kiếm đơn hàng cần đối soát theo mã đơn hàng, mã vận đơn"
        />
      </div>
      <div className="import-excel-btn" onClick={() => onOpenFile()}>
        <div className="btn-icon">
          <svg width="19" height="20" viewBox="0 0 19 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M0.816959 2.06556L12.7579 0.504407C12.8253 0.495556 12.8939 0.500072 12.9593 0.517648C13.0246 0.535225 13.085 0.565452 13.1364 0.60628C13.1879 0.647108 13.2291 0.697583 13.2574 0.754285C13.2857 0.810987 13.3003 0.87259 13.3003 0.93492V19.0652C13.3003 19.1274 13.2857 19.189 13.2575 19.2456C13.2292 19.3022 13.1881 19.3527 13.1367 19.3935C13.0854 19.4343 13.0251 19.4645 12.9599 19.4822C12.8947 19.4998 12.8261 19.5044 12.7588 19.4957L0.816009 17.9346C0.589558 17.905 0.382348 17.8017 0.232443 17.6435C0.0825368 17.4853 2.19493e-06 17.283 0 17.0735V2.92659C2.19493e-06 2.71717 0.0825368 2.51479 0.232443 2.35661C0.382348 2.19843 0.589558 2.09509 0.816009 2.06556H0.816959ZM14.2502 2.17254H18.05C18.302 2.17254 18.5436 2.26417 18.7218 2.42728C18.8999 2.59038 19 2.8116 19 3.04226V16.9579C19 17.1885 18.8999 17.4097 18.7218 17.5728C18.5436 17.736 18.302 17.8276 18.05 17.8276H14.2502V2.17254ZM7.79056 10.0001L10.4504 6.52116H8.17054L6.65062 8.50935L5.13069 6.52116H2.85081L5.51067 10.0001L2.85081 13.479H5.13069L6.65062 11.4908L8.17054 13.479H10.4504L7.79056 10.0001Z" fill="#107C41" />
          </svg>
        </div>
        <div className="separate">
          <svg width="1" height="20" viewBox="0 0 1 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <rect width="1" height="20" fill="#0088FF" />
          </svg>
        </div>
        Tải file Excel đơn hàng
        <input
          type="file"
          className="display-none"
          ref={inputRef}
          accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
          onChange={e => uploadFile(e)}
          multiple
        />
      </div>
      {
        focus ? (
          renderSearch()
        ) : null
      }
    </div>
  );
}

const mapStateToProps = (state) => {
  const { deliveryCollation: { ui: { isEmpty, fetching }, filterInfo, hasOrderCollations: { itemIds } } } = state;
  return {
    itemIds,
    filterInfo,
    // isEmpty,
    // fetching,
  };
};
const mapDispatchToProps = (dispatch) => ({
  fetchOrderCollation: (filterInfo) => dispatch(fetchOrderCollation(filterInfo)),
  addItemOrderCollation: (item) => dispatch(addItemOrderCollation(item)),
  addItemOrderCollationImportFile: (list) => dispatch(addItemOrderCollationImportFile(list)),
  updateFetchingDeliveryCollation: (fetching) => dispatch(updateFetchingDeliveryCollation(fetching)),
  fetchFilterOrderCollation: (filterInfo, page) => dispatch(fetchFilterOrderCollation(filterInfo, page)),
});
export default connect(mapStateToProps, mapDispatchToProps)(Search);