import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'

function TopbarReport (props) {
  // eslint-disable-next-line react/prop-types
  const url = props.location ? props.location.pathname : ''
  if (url.includes('report')) {
    return (
      <div className="wrapper-report-topbar">
        Báo cáo
      </div>
    )
  }
}

export default withRouter(connect(null, null)(TopbarReport))
