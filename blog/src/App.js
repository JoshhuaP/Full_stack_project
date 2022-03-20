import {useEffect, useState} from 'react';
import Swal from "sweetalert2";
import './App.css';
import Header from './components/Header';
import Categories from './components/categories/Categories';
import NewCategory from './components/newCategory/NewCategory';
import './components/categories/categories.css'
import './components/newCategory/newCategory.css'

import Articles from './components/Articles/Articles';
import newArticle from './components/newArticle/NewArticle';
import './components/articles/articles.css'
import './components/newArticle/newArticle.css'

function App() {

  // -----CATEGORY-----
  const [allCategories, setAllCategories] = useState([]);
  const [newCategory, setNewCategory] = useState({
    id: 0,
    name: "",
  });
  const [addCategory, setAddCategory] = useState(false);
  const [toDeleteCategory, setToDeleteCategory] = useState({deleting: false});

  useEffect(() => {
    fetch('http://localhost:8080/api/private/category')
    .then(res => res.json())
    .then(data => setAllCategories(data))
    .catch(e => console.log(e.toString()));
  }, [addCategory]);

  useEffect(() => {
    if (toDeleteCategory.deleting) {
      fetch(`http://localhost:8080/api/private/category/${toDeleteCategory.categoryId}`, {
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
    setAddCategory(true);
  }

  // Function to match a regex to validate name's category field
  function handleChange(event){
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

  // -----ARTICLES-----
  const [allArticles, setAllArticles] = useState([]);
  const [newArticle, setNewArticle] = useState({
    id: 0,
    category: "",
    authorName: "",
    date: "",
    content: ""
  });
  const [addArticle, setAddArticle] = useState(false);
  const [toDeleteArticle, setToDeleteArticle] = useState({deleting: false});

  const [inputInvalid, setInputInvalid] = useState(false);

  useEffect(() => {
    fetch('http://localhost:8080/api/private/article')
    .then(res => res.json())
    .then(data => setAllArticles(data))
    .catch(e => console.log(e.toString()));
  }, [addArticle]);

  useEffect(() => {
    if (toDeleteArticle.deleting) {
      fetch(`http://localhost:8080/api/private/article/${toDeleteArticle.articleId}`, {
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
    setAddArticle(true);
  }

  // Function to match a regex to validate content's article field
  function handleChange(event){
    const {type, content, value} = event.target;

    if (type === "text" && value.match(/^[a-z]+$/)) {
      setNewCategory(prevState => {
        initInvalidInput();
        return {
          ...prevState,
          id: allCategories.length + 1,
          [content]: Text(value)
        }
      })
    } else {
        setInputInvalid("Le contenu de l\'article ne doit contenir que des lettres")
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

  return (
    <div className="App">
      <Header />
      <main>
        <Categories
            data={allCategories}
            deleteCategory={deleteCategory}
        />

        <NewCategory 
          newCategory={newCategory}
          handleChange={handleChange}
          submitCategory={submitCategory}
          inputInvalid={inputInvalid} 
        />

        {/*
        <Articles
            data={allArticles}
            deleteArticle={deleteArticle}
        />

        <NewCategory 
          newArticle={newArticle}
          handleChange={handleChange}
          submitArticle={submitArticle}
          inputInvalid={inputInvalid} 
        /> 
        */}
      </main>
    </div>
  );
}

export default App;
