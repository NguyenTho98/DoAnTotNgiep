import React from 'react'
import classname from 'classname'
import { connect } from 'react-redux'
import { Link, withRouter } from 'react-router-dom'
import './sideBar.scss'
function SideBar (props) {
  const url = props.history.location.pathname
  return (
    <div className={classname('aside')}>
      <ul className='nav menu-top'>

        <li className={url === '/maintenancecard' ? 'active' : ''}>
          <Link to='/maintenancecard'>
            <span>Phiếu sửa chữa</span>
          </Link>
        </li>
        <li className={url === '/customer' ? 'active' : ''}>
          <Link to='/customer'>
            <span>Khách hàng</span>
          </Link>
        </li>
        <li className={url === '/product' ? 'active' : ''}>
          <Link to='/product'>
            <span>Sản phẩm</span>
          </Link>
        </li>
        <li className={url === '/staff' ? 'active' : ''}>
          <Link to='/staff'>
            <span>Nhân viên</span>
          </Link>
        </li>
        <li className={url === '/report' ? 'active' : ''}>
          <Link to='/report-v2'>
            <span>Báo cáo(mới)</span>
          </Link>
        </li>
      </ul>
      <ul className='nav menu-bottom'>
        <li className={url === '/setting/manage-page' ? 'active' : ''}>
          <Link to='/setting/manage-page'>
            <span>Cấu hình</span>
          </Link>
        </li>
        <li className={url === '/newfeatures' ? 'active' : ''}>
          <Link to='/newfeatures'>
            <span>Cập nhật mới</span>
          </Link>
        </li>
        <li className='user'>
          <a href='#' data-toggle='dropdown'>
            <img
              src='https://steamuserimages-a.akamaihd.net/ugc/772858922483239101/AF7361F63549870B02CCEDDEE8C3E70FB90C56D5/'
              alt='zzz'
              onError={(e) => {
                e.target.onerror = null
                e.target.src = '/images/avatar-default.png'
              }}
            />
            <span>Nguyễn Thọ</span>
          </a>
          <div className='dropdown-menu'>
            <div className='info d-flex align-items-center'>
              <div className='avatar'>
                <img
                  src="./../../images/ekko.jpg"
                />
                <span className='status active' />
              </div>
              <div>
                <p>Nguyễn Thọ</p>
                <span className='status active'>Đang hoạt động</span>
              </div>
            </div>
            <ul>
              <li>
                <a
                  href='#'
                  onClick={() =>
                    window.open('https://support.sapo.vn/sapo-social')
                  }
                >
                  Hướng dẫn sử dụng
                </a>
              </li>
              <li>
                <a
                  href='#'
                  onClick={() => window.open('https://support.sapo.vn/')}
                >
                  Trung tâm trợ giúp
                </a>
              </li>
              <li>
                <a href='#' >
                  Đăng xuất
                </a>
              </li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  )
}

export default withRouter(connect(null, null)(SideBar))
