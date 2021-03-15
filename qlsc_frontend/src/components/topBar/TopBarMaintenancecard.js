import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'

function TopBarMaintenancecard (props) {
  const onRedirect = () => {
    // pushstate(props.history, '/manage-staff')
  }

  // eslint-disable-next-line react/prop-types
  const url = props.location ? props.location.pathname : ''
  if (url.includes('maintenancecard-detail')) {
    return (
      <div className="wrapper-maintenancecard-topbar">
        <h1>
          <a onClick={() => onRedirect()} style={{ cursor: 'pointer' }}>
            <span>
              Danh sách phiếu sửa chữa
            </span>
          </a>
        </h1>
        <div className="current">
          <span>Chi tiết phiếu sửa chữa</span>
        </div>
      </div>
    )
  }
  return (
    <div className="wrapper-maintenancecard-topbar">
      Danh sách phiếu sửa chữa
    </div>
  )
}

export default withRouter(connect(null, null)(TopBarMaintenancecard))
