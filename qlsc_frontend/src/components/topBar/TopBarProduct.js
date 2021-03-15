import React from 'react'
import { connect } from 'react-redux'
import { withRouter } from 'react-router-dom'

function TopBarProduct (props) {
  const onRedirect = () => {
    // pushstate(props.history, '/product')
  }

  // eslint-disable-next-line react/prop-types
  const url = props.location ? props.location.pathname : ''
  if (url.includes('staff-detail')) {
    return (
      <div className="wrapper-product-topbar">
        <h1>
          <a onClick={() => onRedirect()} style={{ cursor: 'pointer' }}>
            <span>
              Danh sách sản phẩm
            </span>
          </a>
        </h1>
        <div className="current">
          <span>Chi tiết sản phẩm</span>
        </div>
      </div>
    )
  }
  return (
    <div className="wrapper-product-topbar">
      Danh sách sản phẩm
    </div>
  )
}

export default withRouter(connect(null, null)(TopBarProduct))
