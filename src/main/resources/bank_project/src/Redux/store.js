import { createStore } from "redux";
import { composeWithDevTools } from "redux-devtools-extension";
import appReducer from "./Reducer/AppReducer/appReducer";

export const store = createStore(appReducer, composeWithDevTools());
