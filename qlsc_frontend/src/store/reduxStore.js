import { applyMiddleware, compose, createStore } from 'redux';
import { createLogger } from 'redux-logger';
import thunk from 'redux-thunk';
import reducers from 'reducers';
import fetchMiddleware from 'utils/fetchMiddleware';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose
const middleWare = composeEnhancers(applyMiddleware(
  fetchMiddleware(fetch),
  thunk,
  createLogger({
    predicate: () => {
      return true
    }
  })
))

export default (initState) => {
  const store = createStore(
    reducers,
    initState,
    middleWare
  )
  return store
}
