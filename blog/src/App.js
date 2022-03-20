import {useEffect, useState} from 'react';
import Swal from "sweetalert2";
import {
  BrowserRouter,
  Routes,
  Route,
  Navigate,
  useParams
} from "react-router-dom";
import './App.css';
import Header from './components/Header';
import Categories from './components/categories/Categories';
import NewCategory from './components/newCategory/NewCategory';
import './components/categories/categories.css'
import './components/newCategory/newCategory.css'

import Articles from './components/articles/Articles';
import newArticle from './components/newArticle/NewArticle';
import './components/articles/articles.css'
import './components/newArticle/newArticle.css'
import NewArticles from './components/newArticle/NewArticle';

function App() {
  let params = useParams();
  let categoryId= params.categoryId;

  // -----CATEGORY-----
  const [allCategories, setAllCategories] = useState([]);
  const [newCategory, setNewCategory] = useState({
    id: 0,
    name: "",
  });
  const [postingCategory, setPostingCategory] = useState(false);
  const [toDeleteCategory, setToDeleteCategory] = useState({deleting: false});

  useEffect(() => {
    fetch('http://localhost:9000/categories', {
        headers: {
          'Accept': 'application/json'
        }
    })
    .then(res => res.json())
    .then(data => setAllCategories(data))
    .catch(e => console.log(e.toString()));
  }, [postingCategory]);

  useEffect( () => {
    if (postingCategory) {
      fetch('http://localhost:9000/categories', 
          {
              method: 'POST',
              headers: {
                  'Accept': 'application/json',
                  'Content-type': 'application/json'
              },
              body: JSON.stringify(newCategory)
          }
      )
      .then( (res) => res.json() )
      .then( (data) => {
        setPostingCategory(false);
      })
      .catch( (error) => {
        console.log(error.toString());
      });
    }
  }, [postingCategory]);

  useEffect(() => {
    if (toDeleteCategory.deleting) {
      fetch(`http://localhost:9000/categories/${toDeleteCategory.categoryId}`, {
        method: "DELETE"
      })
      .then(() => {
        setToDeleteCategory({deleting: false})
      })
      .catch(e => console.log(e.toString()))
    }
  }, [toDeleteCategory])

  // Function to add a category
  function submitCategory(){
    setPostingCategory(true);
  }

  // Function to delete a category
  function deleteCategory(event, id) {
    event.stopPropagation();
    Swal.fire({
      title: 'Voulez-vous vraiment supprimer cette catégorie ?',
      icon: 'warning',
      showCancelButton: true,
      cancelButtonColor: '#d33',
      confirmButtonText: 'Confirmer'
    })
    .then((result) => {
      if (result.isConfirmed) {
        setToDeleteCategory({deleting: true, categoryId: id});
        Swal.fire(
          'Supprimé !',
          'La catégorie a été supprimé.',
          'Succès'
        )
      }
    })
  }

  // validate inputs and update newProduct state accordingly
  function handleChangeCategory(event){
    const {name, value} = event.target;
    
    value.match(/^[A-z]*$/) ?
      value.length > 255 ? setInputInvalid("Le nombre maximal de caractères autorisé est de 255")
      : setNewCategory(prevState => {
        initInvalidInput();
        return{
          ...prevState,
          id: allCategories.length + 1,
          [name]: value
        }
      })
    : setInputInvalid("Le nom de la catégorie ne doit contenir que des lettres")
  }

  function showArticles() {
    return <ArticlesList />;
  }

  // -----ARTICLES-----
  const [allArticles, setAllArticles] = useState([]);
  const [newArticle, setNewArticle] = useState({
    id: 0,
    category: "",
    authorName: "",
    date: "",
    content: ""
  });
  const [postingArticle, setPostingArticle] = useState(false);
  const [toDeleteArticle, setToDeleteArticle] = useState({deleting: false});

  const [inputInvalid, setInputInvalid] = useState(false);

  useEffect(() => {
    fetch('http://localhost:9000/articles', {
        headers: {
          'Accept': 'application/json'
        }
    })
    .then(res => res.json())
    .then(data => setAllArticles(data))
    .catch(e => console.log(e.toString()));
  }, [postingArticle]);

  useEffect( () => {
    if (postingArticle) {
      fetch('http://localhost:9000/articles', 
          {
              method: 'POST',
              headers: {
                  'Accept': 'application/json',
                  'Content-type': 'application/json'
              },
              body: JSON.stringify(newCategory)
          }
      )
      .then( (res) => res.json() )
      .then( (data) => {
        setPostingArticle(false);
      })
      .catch( (error) => {
        console.log(error.toString());
      });
    }
  }, [postingArticle]);

  useEffect(() => {
    if (toDeleteArticle.deleting) {
      fetch(`http://localhost:3000/articles/${toDeleteArticle.articleId}`, {
        method: "DELETE"
      })
      .then(() => {
        setToDeleteArticle({deleting: false})
      })
      .catch(e => console.log(e.toString()))
    }
  }, [toDeleteArticle])

  // Function to add an article
  function submitArticle(){
    setPostingArticle(true);
  }

  // Function to match a regex to validate name's category field
  function handleChangeArticle(event){
    const {type, name, value} = event.target;

    if (type === "text" && value.match(/^[a-z]+$/)) {
      setNewArticle(prevState => {
        initInvalidInput();
        return {
          ...prevState,
          id: allArticles.length + 1,
          [name]: Text(value)
        }
      })
    } else {
        setInputInvalid("Le nom de la catégorie ne doit contenir que des lettres")
    }
  }

  // Function to delete an article
  function deleteArticle(event, id) {
    event.stopPropagation();
    Swal.fire({
      title: 'Voulez-vous vraiment supprimer cet article ?',
      icon: 'warning',
      showCancelButton: true,
      cancelButtonColor: '#d33',
      confirmButtonText: 'Confirmer'
    })
    .then((result) => {
      if (result.isConfirmed) {
        setToDeleteArticle({deleting: true, articleId: id});
        Swal.fire(
          'Supprimé !',
          'L\'article a été supprimé.',
          'Succès'
        )
      }
    })
  }

  function initInvalidInput() {
    setInputInvalid(false)
  }

  //Check if url uses a secured protocol
  function validateUrl(url) {
    const parsed = new URL(url);
    return ["https:", "http:"].includes(parsed.protocol);
  }

  return (
    <BrowserRouter>
          <Routes>
            <Route path='/' element={<CategoriesList />} />
            <Route path='categories'>
              <Route path=":id">
                <Route path='articles' element={<ArticlesList />}/>
              </Route> 
            </Route> 
          </Routes>
   </BrowserRouter>
  );

  function CategoriesList() {
    return (
      <div className="App">
      <Header />
      <main>
        <Categories
          data={allCategories}
          validateUrl={validateUrl}
          showArticles={showArticles}
          deleteCategory={deleteCategory}
        />

        <NewCategory 
          newCategory={newCategory}
          handleChange={handleChangeCategory}
          submitCategory={submitCategory}
          inputInvalid={inputInvalid} 
        />
      </main>
    </div>
    );
  }

  function ArticlesList() {
    return (
      <div className="App">
      <Header />
      <main>
        <Articles
          data={allArticles}
          deleteArticle={deleteArticle}
        />
        <NewArticles 
          newArticle={newArticle}
          handleChange={handleChangeArticle}
          submitArticle={submitArticle}
          inputInvalid={inputInvalid} 
        /> 
      </main>
    </div>
    );
  }
}

export default App;
