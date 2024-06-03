import axios, { AxiosPromise } from "axios";
import { useQuery } from "@tanstack/react-query";
import { TakeHomeData } from "../interfaces/TakeHomeData";

const API_URL = 'http://localhost:8080';

const fetchData = async (): AxiosPromise<TakeHomeData[]> => {
    const response = axios.get(API_URL + '/sleep');
    return response;
}

export default function useTakeHomeData() {
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['sleep-data'],
        retry: 2
    })

    return {
        ...query,
        data: query.data?.data
    }

}
