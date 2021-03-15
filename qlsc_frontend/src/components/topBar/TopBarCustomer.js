import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'

function TopbarCustomer (props) {
  const onRedirect = () => {
    // pushstate(props.history, '/manage-staff')
  }

  // eslint-disable-next-line react/prop-types
  const url = props.location ? props.location.pathname : ''
  if (url.includes('customer/create')) {
    return (
      <div className="wrapper-customer-topbar">
        <h1>
          <a onClick={() => onRedirect()} style={{ cursor: 'pointer' }}>
            <span>
              Danh sách khách hàng
            </span>
          </a>
        </h1>
        <div className="current">
          <span>Thêm mới khách hàng</span>
        </div>
      </div>
    )
  }
  if (url.includes('customer/detail')) {
    return (
      <div className="wrapper-customer-topbar">
        <h1>
          <a onClick={() => onRedirect()} style={{ cursor: 'pointer' }}>
            <span>
              Danh sách khách hàng
            </span>
          </a>
        </h1>
        <div className="current">
          <span>Chi tiết khách hàng</span>
        </div>
      </div>
    )
  }
  return (
    <div className="wrapper-customer-topbar">
      Danh sách khách hàng
    </div>
  )
}

export default withRouter(connect(null, null)(TopbarCustomer))
