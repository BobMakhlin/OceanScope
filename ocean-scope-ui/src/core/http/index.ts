import axios from 'axios';
import { API_URL } from '../../../config.ts'

const $api = axios.create({
  withCredentials: false,
  baseURL: API_URL,
});

export default $api;
