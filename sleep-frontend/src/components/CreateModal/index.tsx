import { useEffect, useState } from "react";
import { ButtonSubmit, FormInput, ModalBody, ModalOverlay } from "./styles";

import { TakeHomeData } from "../../TakeHomeData";
import { useTakeHomeMutate } from "../../hooks/useTakeHomeMutate";

interface InputProps {
  label: string;
  value: string | number;
  updateValue(value: any): void;
}

interface ModalProps {
  closeModal(): void;
}

const Input = ({ label, value, updateValue }: InputProps) => {
  return (
    <>
      <label>{label}</label>
      <input
        value={value}
        onChange={(event) => updateValue(event.target.value)}
      ></input>
    </>
  );
};

export default function CreateModal({closeModal}: ModalProps) {
  const [date, setDate] = useState(new Date());
  const [timeBed, setTimeBed] = useState("");
  const [timeWake, setTimeWake] = useState("");
  const [status, setStatus] = useState("");
  const [totalTime, setTotalTime] = useState("");
  const { mutate, isSuccess, isLoading } = useTakeHomeMutate();

  const submit = () => {
    const takeHomeData: TakeHomeData = {
      date,
      timeBed,
      timeWake,
      status,
      totalTime,
    };
    mutate(takeHomeData);
  };

  useEffect(() => {
    if (!isSuccess) return;
    closeModal();
  }, [isSuccess]);

  return (
    <ModalOverlay>
      <ModalBody>
        <h2>Register a New Sleep Time</h2>
        <FormInput>
          <Input
            label="Date"
            value={date.toISOString()}
            updateValue={setDate}
          />
          <Input
            label="Time to Sleep"
            value={timeBed}
            updateValue={setTimeBed}
          />
          <Input
            label="Time to Awake"
            value={timeWake}
            updateValue={setTimeWake}
          />
          <Input
            label="Morning Feeling"
            value={status}
            updateValue={setStatus}
          />
        </FormInput>
        <ButtonSubmit onClick={submit} className="btn-secondary">
          {isLoading ? "posting..." : "posted"}
        </ButtonSubmit>
      </ModalBody>
    </ModalOverlay>
  );
}
