import axios, { AxiosResponse } from "axios";
import { useQuery } from "@tanstack/react-query";
import { TakeHomeAverageData } from "../interfaces/TakeHomeAverageData";

const API_URL = 'http://localhost:8080';

const fetchData = async (): Promise<TakeHomeAverageData> => {
    try {
        const response: AxiosResponse<TakeHomeAverageData> = await axios.get(`${API_URL}/sleep/average`);
        return response.data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            console.error("API fetch error response:", error.response?.data);
        } else {
            console.error("Unexpected error:", error);
        }
        throw error;
    }
};

export default function useTakeHomeAverageData() {
    const query = useQuery<TakeHomeAverageData>({
        queryFn: fetchData,
        queryKey: ['sleep-average'],
        retry: 2
    });

    return {
        ...query,
        data: query.data
    };
}