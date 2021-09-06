import BarChart from "components/BarChart";
import DataTable from "components/DataTable";
import DonutChart from "components/DonutChart";
import Footer from "components/Footer";
import NavBar from "components/NavBar";

function App() {
  return (
    <>
      <NavBar/>
      <div className="container">
        <h1 className="text-primary py-3">Seles Dashboard </h1>

        <div className="row px-3">
          <div className="col-sm-6">
            <h5 className="text-center text-secondary">Success Tax</h5>
            <BarChart/>
          </div>
          
          <div className="col-sm-6">
            <h5 className="text-center text-secondary">All seles</h5>
            <DonutChart/>
          </div>

        <div className="py-3">
          <h2 className="text-primary">All Seles</h2>
          </div>
        </div>
        <DataTable/>
      </div>
      <Footer/>
    </>
    
  );
}

export default App;
