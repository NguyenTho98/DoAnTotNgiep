import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'

function TopBarStaff (props) {
  const onRedirect = () => {
    // pushstate(props.history, '/manage-staff')
  }

  // eslint-disable-next-line react/prop-types
  const url = props.location ? props.location.pathname : ''
  if (url.includes('staff-detail')) {
    return (
      <div className="wrapper-manage-staff-topbar">
        <h1>
          <a onClick={() => onRedirect()} style={{ cursor: 'pointer' }}>
            <span>
              Danh sách nhân viên
            </span>
          </a>
        </h1>
        <div className="current">
          <span>Chi tiết nhân viên</span>
        </div>
      </div>
    )
  }
  return (
    <div className="wrapper-manage-staff-topbar">
      Danh sách nhân viên
    </div>
  )
}

export default withRouter(connect(null, null)(TopBarStaff))
