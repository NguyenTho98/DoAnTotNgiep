import React, { Fragment } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { createBrowserHistory } from 'history'
import SideBar from './components/sideBar/SideBar'
import TopBar from './components/topBar/TopBar'


export const App = () => (
  <Fragment>
    <Router history={createBrowserHistory()}>
      <TopBar />
      <SideBar />
      <Switch>
        {/* <Route exact path="/" component={Home}/>
        <Route path="/about" component={About}/>
        <Route path="/sample" component={Sample}/> */}
        {/* <Route path="/manage-feed" exact render={() => <Staff />} /> */}
      </Switch>
    </Router>
  </Fragment>
)
