import logo from './logo.svg';
import './App.css';
import Header from './components/Header';
import Categories from './components/categories/Categories';
import NewCategory from './components/newCategory/NewCategory';

function App() {
  return (
    <div className="App">
      <Header />

      {/* <main>
        <Categories
            data={allCategories}
            deleteCategory={deleteCategory}
            validateUrl={validateUrl}
            url={sampleUrl}
        />

        <NewCategory 
          newCategory={newCategory}
          handleChange={handleChange}
          submitCategory={submitCategory}
          inputInvalid={inputInvalid} 
        />
      </main> */}
      <main>
        <div className="Categories">
          <div className="Category">
            <p>Test name</p>
          </div>
          <div className="Category">
            <p>Test name</p>
          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
