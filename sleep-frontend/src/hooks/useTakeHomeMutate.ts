import axios, { AxiosPromise } from "axios";
import { useMutation, useQueryClient } from "@tanstack/react-query";

import { TakeHomeData } from "../interfaces/TakeHomeData";

const API_URL = 'http://localhost:8080';

const postData = async (data: TakeHomeData): AxiosPromise<any> => {
    const response = axios.post(API_URL + '/sleep', data);
    return response;
}

export function useTakeHomeMutate() {
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries(['sleep-data'])
        }
    })

    return mutate;
}