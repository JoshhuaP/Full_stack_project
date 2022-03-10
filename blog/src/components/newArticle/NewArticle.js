export default function NewArticles(props) {

    // the inputs are controled by the state in App.js

    return (
        <div className="newArticle__form">
            <h3 className="newArticle__title" >Add an article</h3>
            <div className="newArticle__container">
                <label>Name of the category : {" "} </label>
                <input
                    className="newArticle__input" 
                    type="text"
                    placeholder="Choose a category name" 
                    name="name"
                    value={props.newArticle.name}
                    onChange={props.handleChange} 
                />
            </div>
            
            <div className="newArticle__container">
                <label>Prix du produit : {" "}</label>
                <input 
                    className="newArticle__input"
                    type="number" 
                    name="cost" 
                    value={props.newArticle.cost}
                    onChange={props.handleChange} 
                /> 
            </div>
            
            <textarea
                className="newArticle__description"
                placeholder="Description du produit" 
                name="description"
                value={props.newArticle.description}
                onChange={props.handleChange} 
            />

            {props.inputInvalid && <p>{props.inputInvalid}</p>}

            <button className="newArticle__submitButton" onClick={props.submitProduct}>New article</button>
        </div>
    )
}

