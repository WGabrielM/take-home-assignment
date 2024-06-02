import { useState } from "react";
import { PlusCircle } from "phosphor-react";
import { ThemeProvider } from "styled-components";

import "./App.css";

import Card from "./components/Card";
import useTakeHomeData from "./hooks/useTakeHomeData";
import { defaultTheme } from "./styles/themes/default";
import CreateModal from "./components/CreateModal";

function App() {
  const { data } = useTakeHomeData();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () => {
    setIsModalOpen((prev) => !prev);
  };

  return (
    <ThemeProvider theme={defaultTheme}>
      <div className="container">
        <h1>Take-home Assignment</h1>
        <div className="card-display">
          <div className="card-add">
            <header>No sleep information</header>
            {isModalOpen && <CreateModal closeModal={handleOpenModal} />}
            <button onClick={handleOpenModal}>
              <PlusCircle size={205}  />
            </button>
          </div>
          {data?.map((takeHomeData) => (
            <Card
              key={takeHomeData.id}
              date={takeHomeData.date}
              timeBed={takeHomeData.timeBed}
              timeWake={takeHomeData.timeWake}
              status={takeHomeData.status}
              totalTime={takeHomeData.totalTime}
            />
          ))}
        </div>
      </div>
    </ThemeProvider>
  );
}

export default App;
