export default function NewProduct(props) {

    // the inputs are controled by the state in App.js

    return (
        <div className="newCategory__form">
            <h3 className="newCategory__title" >Add a category</h3>
            <div className="newCategory__container">
                <label>Name of the category : {" "} </label>
                <input
                    className="newCategory__input" 
                    type="text"
                    placeholder="Choose a name" 
                    name="name"
                    value={props.newCategory.name}
                    onChange={props.handleChange} 
                />
            </div>
            {props.inputInvalid && <p>{props.inputInvalid}</p>}

            <button className="newCategory__submitButton" onClick={props.submitCategory}>New Category</button>
        </div>
    )
}