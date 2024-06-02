import{ useEffect, useState } from 'react'
import { ButtonSubmit, FormInput, ModalBody, ModalOverlay } from './styles';

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

export default function CreateModal() {
    const [title, setTitle] = useState("");
    const [price, setPrice] = useState(0);
    const [image, setImage] = useState("");
    const { mutate, isSuccess, isLoading } = useFoodDataMutate();
  
    const submit = () => {
      const foodData: FoodData = {
        title,
        price,
        image,
      };
      mutate(foodData);
    };
  
    useEffect(() => {
      if (!isSuccess) return;
      closeModal();
    }, [isSuccess]);
  
    return (
      <ModalOverlay>
        <ModalBody>
          <h2>Cadastre um novo item no cardápio</h2>
          <FormInput>
            <Input label="title" value={title} updateValue={setTitle} />
            <Input label="price" value={price} updateValue={setPrice} />
            <Input label="image" value={image} updateValue={setImage} />
          </FormInput>
          <ButtonSubmit onClick={submit} className="btn-secondary">
            {isLoading ? "posting..." : "posted"}
          </ButtonSubmit>
        </ModalBody>
      </ModalOverlay>
    );
}
