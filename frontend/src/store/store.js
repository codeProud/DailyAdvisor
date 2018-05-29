import Vue from 'vue';
import Vuex from 'vuex';
import auth from './services/auth';

import authModule from './authModule';

Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
        authModule,
    },
});
