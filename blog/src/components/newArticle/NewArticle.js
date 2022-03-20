export default function NewArticles(props) {
    return (
        <div className="newArticle__form">
            <h3 className="newArticle__title" >Add an article</h3>

            <div className="newArticle__container">
                <label>Nom de la catégorie {" "}</label>
                <input 
                    className="newArticle__input"
                    type="number" 
                    name="categoryName" 
                    value={props.newArticle.categoryId}
                    onChange={props.handleChange} 
                /> 
            </div>

            <div className="newArticle__container">
                <label>Nom de l'auteur de l'article : {" "}</label>
                <input 
                    className="newArticle__input"
                    type="number" 
                    name="author" 
                    value={props.newArticle.author}
                    onChange={props.handleChange} 
                /> 
            </div>
            
            <div className="newArticle__container">
                <label>Article posté le : {" "} </label>
                <input
                    className="newArticle__input" 
                    type="text"
                    name="date"
                    value={props.newArticle.date}
                    onChange={props.handleChange} 
                />
            </div>

            <label>Contenu :</label>
            <textarea
                className="newArticle__contenu"
                placeholder="Contenu du produit" 
                name="contenu"
                value={props.newArticle.content}
                onChange={props.handleChange} 
            />

            {props.inputInvalid && <p>{props.inputInvalid}</p>}

            <button className="newArticle__submitButton" onClick={props.submitProduct}>New article</button>
        </div>
    )
}

